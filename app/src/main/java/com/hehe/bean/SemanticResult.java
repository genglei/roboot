package com.hehe.bean;

import java.util.List;

/* loaded from: classes.dex */
public class SemanticResult {
    private List<BehaviorsBean> behaviors;

    public List<BehaviorsBean> getBehaviors() {
        return this.behaviors;
    }

    public void setBehaviors(List<BehaviorsBean> list) {
        this.behaviors = list;
    }

    /* loaded from: classes.dex */
    public static class BehaviorsBean {
        private EmotionBean emotion;
        private String exception;
        private long globalId;
        private IntentBean intent;
        private List<ResultsBean> results;
        private List<SequencesBean> sequences;

        public EmotionBean getEmotion() {
            return this.emotion;
        }

        public void setEmotion(EmotionBean emotionBean) {
            this.emotion = emotionBean;
        }

        public String getException() {
            return this.exception;
        }

        public void setException(String str) {
            this.exception = str;
        }

        public long getGlobalId() {
            return this.globalId;
        }

        public void setGlobalId(long j) {
            this.globalId = j;
        }

        public IntentBean getIntent() {
            return this.intent;
        }

        public void setIntent(IntentBean intentBean) {
            this.intent = intentBean;
        }

        public List<ResultsBean> getResults() {
            return this.results;
        }

        public void setResults(List<ResultsBean> list) {
            this.results = list;
        }

        public List<SequencesBean> getSequences() {
            return this.sequences;
        }

        public void setSequences(List<SequencesBean> list) {
            this.sequences = list;
        }

        /* loaded from: classes.dex */
        public static class EmotionBean {
            private int answerEmotionId;

            public int getAnswerEmotionId() {
                return this.answerEmotionId;
            }

            public void setAnswerEmotionId(int i) {
                this.answerEmotionId = i;
            }
        }

        /* loaded from: classes.dex */
        public static class IntentBean {
            private String appKey;
            private int code;
            private String operateState;
            private ParametersBean parameters;
            private String type;

            public String getOperateState() {
                return this.operateState;
            }

            public String getAppKey() {
                return this.appKey;
            }

            public void setAppKey(String str) {
                this.appKey = str;
            }

            public int getCode() {
                return this.code;
            }

            public void setCode(int i) {
                this.code = i;
            }

            public ParametersBean getParameters() {
                return this.parameters;
            }

            public void setParameters(ParametersBean parametersBean) {
                this.parameters = parametersBean;
            }

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }

            /* loaded from: classes.dex */
            public static class ParametersBean {
                private String duration;
                private String id;
                private String name;
                private String originalSinger;
                private String originalSong;
                private String singer;
                private String song;
                private String text_spare;
                private String url;

                public String getSong() {
                    return this.song;
                }

                public void setSong(String str) {
                    this.song = str;
                }

                public String getDuration() {
                    return this.duration;
                }

                public void setDuration(String str) {
                    this.duration = str;
                }

                public String getSinger() {
                    return this.singer;
                }

                public void setSinger(String str) {
                    this.singer = str;
                }

                public String getOriginalSinger() {
                    return this.originalSinger;
                }

                public void setOriginalSinger(String str) {
                    this.originalSinger = str;
                }

                public String getOriginalSong() {
                    return this.originalSong;
                }

                public void setOriginalSong(String str) {
                    this.originalSong = str;
                }

                public String getName() {
                    return this.name;
                }

                public void setName(String str) {
                    this.name = str;
                }

                public String getText_spare() {
                    return this.text_spare;
                }

                public void setText_spare(String str) {
                    this.text_spare = str;
                }

                public String getId() {
                    return this.id;
                }

                public void setId(String str) {
                    this.id = str;
                }

                public String getUrl() {
                    return this.url;
                }

                public void setUrl(String str) {
                    this.url = str;
                }
            }
        }

        /* loaded from: classes.dex */
        public static class ResultsBean {
            private String resultType;
            private ValuesBean values;

            public String getResultType() {
                return this.resultType;
            }

            public void setResultType(String str) {
                this.resultType = str;
            }

            public ValuesBean getValues() {
                return this.values;
            }

            public void setValues(ValuesBean valuesBean) {
                this.values = valuesBean;
            }

            /* loaded from: classes.dex */
            public static class ValuesBean {
                private String text;

                public String getText() {
                    return this.text;
                }

                public void setText(String str) {
                    this.text = str;
                }
            }
        }

        /* loaded from: classes.dex */
        public static class SequencesBean {
            private String service;

            public String getService() {
                return this.service;
            }

            public void setService(String str) {
                this.service = str;
            }
        }
    }
}
