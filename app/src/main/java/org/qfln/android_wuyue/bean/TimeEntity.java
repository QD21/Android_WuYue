package org.qfln.android_wuyue.bean;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/1 14:26
 * @备注：
 */
public class TimeEntity {


    /**
     * code : 200
     * data : {"schedules":{"item":["8:00","12:00"],"post":["8:00","10:30","12:00","16:00"]}}
     * message : OK
     */

    private int code;
    /**
     * schedules : {"item":["8:00","12:00"],"post":["8:00","10:30","12:00","16:00"]}
     */

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
        private SchedulesEntity schedules;

        public void setSchedules(SchedulesEntity schedules) {
            this.schedules = schedules;
        }

        public SchedulesEntity getSchedules() {
            return schedules;
        }

        public static class SchedulesEntity {
            private List<String> item;
            private List<String> post;

            public void setItem(List<String> item) {
                this.item = item;
            }

            public void setPost(List<String> post) {
                this.post = post;
            }

            public List<String> getItem() {
                return item;
            }

            public List<String> getPost() {
                return post;
            }
        }
    }
}
