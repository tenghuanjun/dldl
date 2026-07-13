-- 为 accounts 表增加 platform 字段，区分 37 系与 28 手游账号
-- 在 Supabase SQL Editor 执行（前端使用 anon key，无 ALTER 权限）
-- 执行后：旧账号默认 '37'，新增账号按前端传入值

ALTER TABLE public.accounts
    ADD COLUMN IF NOT EXISTS platform text NOT NULL DEFAULT '37';

-- 校验
-- SELECT column_name, data_type, column_default
-- FROM information_schema.columns
-- WHERE table_name = 'accounts' AND column_name = 'platform';
