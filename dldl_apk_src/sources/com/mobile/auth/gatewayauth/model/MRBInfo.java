package com.mobile.auth.gatewayauth.model;

import com.mobile.auth.gatewayauth.ExceptionProcessor;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class MRBInfo {
    private AlibabaAliqinPnsMobileGetResponse alibaba_aliqin_pns_mobile_get_response;

    public static class AlibabaAliqinPnsMobileGetResponse {
        private String request_id;
        private Result result;

        public static class Result {
            private String code;
            private String message;
            private Module module;

            public static class Module {
                private String mobile;

                public String getMobile() {
                    try {
                        return this.mobile;
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

                public void setMobile(String str) {
                    try {
                        this.mobile = str;
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                public String toString() {
                    try {
                        return "Module{mobile='" + this.mobile + "'}";
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

            public String getCode() {
                try {
                    return this.code;
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

            public String getMessage() {
                try {
                    return this.message;
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

            public Module getModule() {
                try {
                    return this.module;
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

            public void setCode(String str) {
                try {
                    this.code = str;
                } catch (Throwable th) {
                    try {
                        ExceptionProcessor.processException(th);
                    } catch (Throwable th2) {
                        ExceptionProcessor.processException(th2);
                    }
                }
            }

            public void setMessage(String str) {
                try {
                    this.message = str;
                } catch (Throwable th) {
                    try {
                        ExceptionProcessor.processException(th);
                    } catch (Throwable th2) {
                        ExceptionProcessor.processException(th2);
                    }
                }
            }

            public void setModule(Module module) {
                try {
                    this.module = module;
                } catch (Throwable th) {
                    try {
                        ExceptionProcessor.processException(th);
                    } catch (Throwable th2) {
                        ExceptionProcessor.processException(th2);
                    }
                }
            }

            public String toString() {
                try {
                    return "Result{code='" + this.code + "', message='" + this.message + "', module=" + this.module + '}';
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

        public String getRequest_id() {
            try {
                return this.request_id;
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

        public Result getResult() {
            try {
                return this.result;
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

        public void setRequest_id(String str) {
            try {
                this.request_id = str;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }

        public void setResult(Result result) {
            try {
                this.result = result;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }
    }

    public AlibabaAliqinPnsMobileGetResponse getAlibaba_aliqin_pns_mobile_get_response() {
        try {
            return this.alibaba_aliqin_pns_mobile_get_response;
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

    public void setAlibaba_aliqin_pns_mobile_get_response(AlibabaAliqinPnsMobileGetResponse alibabaAliqinPnsMobileGetResponse) {
        try {
            this.alibaba_aliqin_pns_mobile_get_response = alibabaAliqinPnsMobileGetResponse;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public String toString() {
        try {
            return "MRBInfo{alibaba_aliqin_pns_mobile_get_response=" + this.alibaba_aliqin_pns_mobile_get_response + '}';
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
