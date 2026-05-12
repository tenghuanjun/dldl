/**
 * 为 npm 脚本补齐 ~/.cargo/bin（图形界面 / IDE 终端常未加载 shell 配置），
 * 再校验 cargo 并以相同环境启动本地 @tauri-apps/cli。
 */
import { spawnSync } from "node:child_process";
import path from "node:path";
import fs from "node:fs";
import { fileURLToPath } from "node:url";

const root = path.join(path.dirname(fileURLToPath(import.meta.url)), "..");
const homedir = process.env.HOME || process.env.USERPROFILE || "";
const cargoBin = path.join(homedir, ".cargo", "bin");

if (fs.existsSync(cargoBin)) {
  process.env.PATH = `${cargoBin}${path.delimiter}${process.env.PATH || ""}`;
}

const cargoName = process.platform === "win32" ? "cargo.exe" : "cargo";
const cargoPath = path.join(cargoBin, cargoName);
const cargoCmd = fs.existsSync(cargoPath) ? cargoPath : "cargo";

const check = spawnSync(cargoCmd, ["--version"], {
  encoding: "utf8",
  env: process.env,
  stdio: "pipe",
});
if (check.status !== 0) {
  console.error("\n[错误] 未找到 cargo：Tauri 依赖 Rust 工具链。\n");
  console.error("  安装：");
  console.error("    macOS/Linux: https://rustup.rs");
  console.error("    Windows:     https://www.rust-lang.org/tools/install\n");
  console.error("  若已安装仍报错，请在新终端执行:");
  console.error('    source "$HOME/.cargo/env"');
  console.error("  并确认存在:  ~/.cargo/bin/cargo\n");
  process.exit(1);
}

const tauriArgs = process.argv.slice(2);
if (!tauriArgs.length) {
  console.error("用法: node scripts/tauri-run.mjs <dev|build|...> [-- 其它 tauri 参数]");
  process.exit(1);
}

// macOS DMG：Tauri 调用的 bundle_dmg.sh 默认用 osascript 驱动 Finder 排版；
// 在部分环境（自动化权限、远程会话等）会失败。官方在 CI=true 时会追加
// --skip-jenkins，跳过 Finder 步骤仍可生成 DMG。未显式设置 CI 且未要求 Finder
// 排版时，本地 darwin 打包默认走该路径。需要 Finder 排版时：
//   TAURI_DMG_USE_FINDER=1 npm run tauri:build
if (
  process.platform === "darwin" &&
  tauriArgs[0] === "build" &&
  process.env.CI === undefined &&
  !process.env.TAURI_DMG_USE_FINDER
) {
  process.env.CI = "true";
}

const r = spawnSync("npx", ["tauri", ...tauriArgs], {
  cwd: root,
  stdio: "inherit",
  env: process.env,
  shell: process.platform === "win32",
});

process.exit(r.status === null ? 1 : r.status);
