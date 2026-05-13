/**
 * macOS：为随包分发的 Node、以及 node_modules 下的 .node/.dylib 做 ad-hoc 签名，
 * 否则在「加固运行时」下子进程 Node 加载 sqlite3 等原生模块时可能被系统拒绝（进程秒退，访达表现为打不开）。
 * 非 darwin 平台直接退出 0。
 */
import { spawnSync } from "node:child_process";
import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const root = path.join(__dirname, "..");

function codesignOne(file) {
  const st = fs.lstatSync(file);
  if (st.isSymbolicLink()) return;
  const r = spawnSync(
    "codesign",
    ["--sign", "-", "--force", "--timestamp=none", file],
    { stdio: "inherit", env: process.env }
  );
  if (r.status !== 0) {
    throw new Error(`codesign 失败: ${file}`);
  }
}

function walkSignNative(dir, signed) {
  if (!fs.existsSync(dir)) return;
  const entries = fs.readdirSync(dir, { withFileTypes: true });
  for (const ent of entries) {
    const p = path.join(dir, ent.name);
    if (ent.isSymbolicLink()) continue;
    if (ent.isDirectory()) {
      if (ent.name === ".git") continue;
      walkSignNative(p, signed);
    } else if (p.endsWith(".node")) {
      codesignOne(p);
      signed.push(p);
    } else if (
      p.endsWith(".dylib") &&
      p.includes(`${path.sep}sqlite3${path.sep}`)
    ) {
      codesignOne(p);
      signed.push(p);
    }
  }
}

if (process.platform !== "darwin") {
  process.exit(0);
}

const signed = [];
const nm = path.join(root, "node_modules");
if (fs.existsSync(nm)) {
  console.log("[codesign-macos-bundle-deps] 正在为 node_modules 内原生库签名…");
  walkSignNative(nm, signed);
  console.log(`[codesign-macos-bundle-deps] 已签名 ${signed.length} 个文件`);
}

const embeddedNode = path.join(root, "src-tauri", "_embed", "nodejs", "bin", "node");
if (fs.existsSync(embeddedNode)) {
  console.log("[codesign-macos-bundle-deps] 正在为内置 node 签名…");
  codesignOne(embeddedNode);
}
