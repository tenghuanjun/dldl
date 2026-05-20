/**
 * 在 `tauri build` 的 beforeBuildCommand 阶段下载官方 Node 二进制，
 * 解压到 `src-tauri/_embed/nodejs/`，随应用打包；用户机器无需安装 Node。
 *
 * 目标平台由 Tauri 注入的 TAURI_ENV_* 决定（与当前 cargo target 一致）。
 * 版本：环境变量 DLDL_EMBED_NODE_VERSION（默认与下方常量一致）。
 */
import { spawnSync } from "node:child_process";
import fs from "node:fs";
import path from "node:path";
import { pipeline } from "node:stream/promises";
import { createWriteStream } from "node:fs";
import { fileURLToPath } from "node:url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const root = path.join(__dirname, "..");
const embedRoot = path.join(root, "src-tauri", "_embed", "nodejs");
const cacheDir = path.join(root, "node_modules", ".cache", "dldl-embed-node");

const DEFAULT_NODE_VERSION = "22.14.0";

function resolveDistKey() {
  const p = (process.env.TAURI_ENV_PLATFORM || process.platform || "").toLowerCase();
  const a = (process.env.TAURI_ENV_ARCH || process.arch || "").toLowerCase();

  const isDarwin = p.includes("darwin") || p === "macos";
  const isWin = p.includes("win");
  const isLinux = p.includes("linux") || p === "freebsd";

  const isArm = a === "arm64" || a === "aarch64";
  if (isDarwin) {
    return isArm ? "darwin-arm64" : "darwin-x64";
  }
  if (isWin) {
    return isArm ? "win-arm64" : "win-x64";
  }
  if (isLinux) {
    return isArm ? "linux-arm64" : "linux-x64";
  }
  console.warn(
    `[ensure-bundled-node] 未识别的 TAURI_ENV_PLATFORM=${process.env.TAURI_ENV_PLATFORM}，按 linux-x64 处理`
  );
  return "linux-x64";
}

function rimraf(dir) {
  if (fs.existsSync(dir)) {
    fs.rmSync(dir, { recursive: true, force: true });
  }
}

async function downloadToFile(url, dest) {
  const res = await fetch(url, { redirect: "follow" });
  if (!res.ok || !res.body) {
    throw new Error(`下载失败 ${res.status}: ${url}`);
  }
  await fs.promises.mkdir(path.dirname(dest), { recursive: true });
  await pipeline(res.body, createWriteStream(dest));
}

function extractArchive(archivePath, outDir) {
  fs.mkdirSync(outDir, { recursive: true });
  const isZip =
    archivePath.toLowerCase().endsWith(".zip") || path.extname(archivePath).toLowerCase() === ".zip";
  const flag = isZip ? "-xf" : "-xzf";
  const r = spawnSync("tar", [flag, archivePath, "-C", outDir], {
    stdio: "inherit",
    env: process.env,
  });
  if (r.error) {
    throw r.error;
  }
  if (r.status !== 0) {
    throw new Error(`解压失败（tar exit ${r.status}）：${archivePath}`);
  }
}

function findTopDir(extractDir) {
  const names = fs.readdirSync(extractDir).filter((n) => !n.startsWith("."));
  if (names.length !== 1) {
    throw new Error(`解压后根目录异常，期望单一顶层目录，得到：${names.join(", ")}`);
  }
  return path.join(extractDir, names[0]);
}

function installFromCache(cachePath, distKey, version, base) {
  const tmp = path.join(cacheDir, `.extract-${base}-${Date.now()}`);
  rimraf(tmp);
  fs.mkdirSync(tmp, { recursive: true });
  try {
    extractArchive(cachePath, tmp);
    const top = findTopDir(tmp);
    rimraf(embedRoot);
    fs.mkdirSync(embedRoot, { recursive: true });

    if (distKey.startsWith("win")) {
      const exe = path.join(top, "node.exe");
      if (!fs.existsSync(exe)) {
        throw new Error(`未找到 ${exe}`);
      }
      fs.copyFileSync(exe, path.join(embedRoot, "node.exe"));
    } else {
      const binNode = path.join(top, "bin", "node");
      if (!fs.existsSync(binNode)) {
        throw new Error(`未找到 ${binNode}`);
      }
      fs.mkdirSync(path.join(embedRoot, "bin"), { recursive: true });
      const dest = path.join(embedRoot, "bin", "node");
      fs.copyFileSync(binNode, dest);
      try {
        fs.chmodSync(dest, 0o755);
      } catch {
        /* ignore */
      }
    }
    console.log(`[ensure-bundled-node] 已写入 ${embedRoot}（${distKey}，v${version}）`);
  } finally {
    rimraf(tmp);
  }
}

async function main() {
  // 默认使用系统 Node.js，设置 DLDL_USE_EMBED_NODE=1 从网上下载
  if (process.env.DLDL_USE_EMBED_NODE !== "1") {
    console.log(
      "[ensure-bundled-node] 使用系统 Node.js 复制到 _embed（设置 DLDL_USE_EMBED_NODE=1 从 nodejs.org 下载）。"
    );
    rimraf(embedRoot);
    fs.mkdirSync(embedRoot, { recursive: true });
    const src = process.execPath;
    if (process.platform === "win32") {
      fs.copyFileSync(src, path.join(embedRoot, "node.exe"));
    } else {
      fs.mkdirSync(path.join(embedRoot, "bin"), { recursive: true });
      const dest = path.join(embedRoot, "bin", "node");
      fs.copyFileSync(src, dest);
      try {
        fs.chmodSync(dest, 0o755);
      } catch {
        /* ignore */
      }
    }
    console.log(`[ensure-bundled-node] 已写入 ${embedRoot}`);
    return;
  }

  const version = process.env.DLDL_EMBED_NODE_VERSION || DEFAULT_NODE_VERSION;
  const distKey = resolveDistKey();
  const ext = distKey.startsWith("win") ? "zip" : "tar.gz";
  const base = `node-v${version}-${distKey}`;
  const fileName = `${base}.${ext}`;
  const url = `https://nodejs.org/dist/v${version}/${fileName}`;

  fs.mkdirSync(cacheDir, { recursive: true });
  const cachePath = path.join(cacheDir, fileName);

  if (!fs.existsSync(cachePath)) {
    console.log(`[ensure-bundled-node] 下载 ${url}`);
    await downloadToFile(url, cachePath);
  } else {
    console.log(`[ensure-bundled-node] 使用缓存 ${cachePath}`);
  }

  installFromCache(cachePath, distKey, version, base);
}

main().catch((e) => {
  console.error("[ensure-bundled-node] 失败:", e.message || e);
  process.exit(1);
});
