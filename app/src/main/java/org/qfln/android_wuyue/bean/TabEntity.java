package org.qfln.android_wuyue.bean;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/1 09:13
 * @备注：
 */
public class TabEntity {

    /**
     * code : 200
     * data : {"candidates":[{"editable":true,"id":9,"name":"送男票"},{"editable":true,"id":5,"name":"送闺蜜"},{"editable":true,"id":26,"name":"送基友"},{"editable":true,"id":6,"name":"送爸妈"},{"editable":true,"id":17,"name":"送同事"},{"editable":true,"id":24,"name":"送宝贝"},{"editable":true,"id":30,"name":"生日"},{"editable":true,"id":31,"name":"纪念日"},{"editable":true,"id":36,"name":"感谢"},{"editable":true,"id":35,"name":"乔迁礼物"},{"editable":true,"id":3,"name":"手工"},{"editable":true,"id":120,"name":"涨姿势"}],"channels":[{"editable":false,"id":104,"name":"精选"},{"editable":true,"id":9,"name":"送男票"},{"editable":true,"id":5,"name":"送闺蜜"},{"editable":true,"id":26,"name":"送基友"},{"editable":true,"id":6,"name":"送爸妈"},{"editable":true,"id":17,"name":"送同事"},{"editable":true,"id":24,"name":"送宝贝"},{"editable":true,"id":30,"name":"生日"},{"editable":true,"id":31,"name":"纪念日"},{"editable":true,"id":36,"name":"感谢"},{"editable":true,"id":35,"name":"乔迁礼物"},{"editable":true,"id":3,"name":"手工"},{"editable":true,"id":120,"name":"涨姿势"}]}
     * message : OK
     */

    private int code;
    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * editable : true
         * id : 9
         * name : 送男票
         */

        private List<CandidatesEntity> candidates;
        /**
         * editable : false
         * id : 104
         * name : 精选
         */

        private List<ChannelsEntity> channels;

        public void setCandidates(List<CandidatesEntity> candidates) {
            this.candidates = candidates;
        }

        public void setChannels(List<ChannelsEntity> channels) {
            this.channels = channels;
        }

        public List<CandidatesEntity> getCandidates() {
            return candidates;
        }

        public List<ChannelsEntity> getChannels() {
            return channels;
        }

        public static class CandidatesEntity {
            private boolean editable;
            private int id;
            private String name;

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isEditable() {
                return editable;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }

        public static class ChannelsEntity {
            private boolean editable;
            private int id;
            private String name;

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isEditable() {
                return editable;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
    }
}
