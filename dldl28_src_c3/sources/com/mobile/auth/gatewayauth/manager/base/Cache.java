package com.mobile.auth.gatewayauth.manager.base;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.jsoner.JSONUtils;
import com.nirvana.tools.jsoner.JsonType;
import com.nirvana.tools.jsoner.Jsoner;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Cache<T> implements Serializable {
    private long expiredTime;
    private String key;
    private T value;

    public static final class a<T> {
        private String a;
        private T b;
        private long c;

        private a() {
        }

        static /* synthetic */ String a(a aVar) {
            try {
                return aVar.a;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        }

        static /* synthetic */ Object b(a aVar) {
            try {
                return aVar.b;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        }

        static /* synthetic */ long c(a aVar) {
            try {
                return aVar.c;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return -1L;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return -1L;
                }
            }
        }

        public a a(long j) {
            try {
                this.c = j;
                return this;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        }

        public a a(T t) {
            try {
                this.b = t;
                return this;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        }

        public a a(String str) {
            try {
                this.a = str;
                return this;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        }

        public Cache a() {
            try {
                return new Cache(this);
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        }
    }

    public Cache() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Cache(a<T> aVar) {
        setKey(a.a((a) aVar));
        setValue(a.b(aVar));
        setExpiredTime(a.c(aVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Cache<T> fromJson(JSONObject jSONObject, JsonType<T> jsonType) {
        try {
            Cache<T> cache = (Cache<T>) new Cache();
            ArrayList arrayList = new ArrayList();
            JSONUtils.fromJson(jSONObject, cache, arrayList);
            if (arrayList.size() > 0) {
                T tNewInstance = jsonType.newInstance();
                if (tNewInstance instanceof Jsoner) {
                    ((Jsoner) tNewInstance).fromJson(jSONObject.optJSONObject("value"));
                    cache.setValue(tNewInstance);
                } else if (JSONUtils.isOriginalBoolean(tNewInstance.getClass()) || JSONUtils.isOriginalChar(tNewInstance.getClass()) || JSONUtils.isOriginalNumber(tNewInstance.getClass()) || JSONUtils.isOriginalString(tNewInstance.getClass())) {
                    cache.setValue(jSONObject.opt("value"));
                }
            }
            return cache;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static a newIpCache() {
        try {
            return new a();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public synchronized void clear() {
        try {
            this.key = null;
            this.value = null;
            this.expiredTime = 0L;
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    public synchronized long getExpiredTime() {
        try {
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1L;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1L;
            }
        }
        return this.expiredTime;
    }

    public synchronized String getKey() {
        try {
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        return this.key;
    }

    public synchronized T getValue() {
        try {
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        return this.value;
    }

    public synchronized boolean isValid() {
        try {
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
        return System.currentTimeMillis() < this.expiredTime;
    }

    public synchronized void setExpiredTime(long j) {
        try {
            this.expiredTime = j;
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    public synchronized void setKey(String str) {
        try {
            this.key = str;
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    public synchronized void setValue(T t) {
        try {
            this.value = t;
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    public JSONObject toJson() {
        Class<?> cls;
        T t;
        Object json;
        try {
            JSONObject json2 = JSONUtils.toJson(this, new ArrayList());
            try {
                cls = this.value.getClass();
                t = this.value;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (!(t instanceof Jsoner)) {
                if (JSONUtils.isOriginalBoolean(cls) || JSONUtils.isOriginalChar(cls) || JSONUtils.isOriginalNumber(cls) || JSONUtils.isOriginalString(cls)) {
                    json = this.value;
                }
                return json2;
            }
            json = ((Jsoner) t).toJson();
            json2.put("value", json);
            return json2;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
