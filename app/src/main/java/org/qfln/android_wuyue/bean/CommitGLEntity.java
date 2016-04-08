package org.qfln.android_wuyue.bean;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/7 17:41
 * @备注：
 */
public class CommitGLEntity {

    /**
     * code : 200
     * data : {"comments":[{"content":"占座","created_at":1459564121,"does_like":false,"id":903486,"likes_count":2,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://img03.liwushuo.com/avatar/151010/ed0e691df_a-w180","can_mobile_login":true,"guest_uuid":null,"id":3002827,"nickname":"老王","role":0}},{"content":"一份在家可以逍遥自在收票子的职业 如果你也想到处旅游追星清空购物车可是缺票子的话！就带着你的决心和态度来找我吧学生党上班族宝妈可以来找我 带你赚钱赚到手软。加wei信bb88740740了解哦","created_at":1459437823,"does_like":false,"id":891842,"likes_count":2,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/20160331/svtaul9a6_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":6900471,"nickname":"扎西德勒","role":0}},{"content":"赞我","created_at":1459314253,"does_like":false,"id":886423,"likes_count":2,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/160404/e1ceffd49_a-w180","can_mobile_login":true,"guest_uuid":null,"id":5934522,"nickname":"果果","role":0}},{"content":"沙发","created_at":1459312961,"does_like":false,"id":886393,"likes_count":11,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/160202/5f00cfb4b_a-w180","can_mobile_login":false,"guest_uuid":null,"id":2565035,"nickname":"seven","role":0}},{"content":"，","created_at":1459303670,"does_like":false,"id":886333,"likes_count":3,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://ww3.sinaimg.cn/crop.0.0.720.720.1024/005ISW","can_mobile_login":false,"guest_uuid":null,"id":5697617,"nickname":"Thesongjust","role":0}},{"content":"沙发\u2026\u2026","created_at":1459303620,"does_like":false,"id":886332,"likes_count":3,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://ww3.sinaimg.cn/crop.0.0.720.720.1024/005ISW","can_mobile_login":false,"guest_uuid":null,"id":5697617,"nickname":"Thesongjust","role":0}},{"content":"沙发","created_at":1459303608,"does_like":false,"id":886330,"likes_count":3,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://ww3.sinaimg.cn/crop.0.0.720.720.1024/005ISW","can_mobile_login":false,"guest_uuid":null,"id":5697617,"nickname":"Thesongjust","role":0}},{"content":"沙发","created_at":1459303589,"does_like":false,"id":886329,"likes_count":3,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://ww3.sinaimg.cn/crop.0.0.720.720.1024/005ISW","can_mobile_login":false,"guest_uuid":null,"id":5697617,"nickname":"Thesongjust","role":0}}],"paging":{"next_url":null}}
     * message : OK
     */

    private int code;
    /**
     * comments : [{"content":"占座","created_at":1459564121,"does_like":false,"id":903486,"likes_count":2,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://img03.liwushuo.com/avatar/151010/ed0e691df_a-w180","can_mobile_login":true,"guest_uuid":null,"id":3002827,"nickname":"老王","role":0}},{"content":"一份在家可以逍遥自在收票子的职业 如果你也想到处旅游追星清空购物车可是缺票子的话！就带着你的决心和态度来找我吧学生党上班族宝妈可以来找我 带你赚钱赚到手软。加wei信bb88740740了解哦","created_at":1459437823,"does_like":false,"id":891842,"likes_count":2,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/20160331/svtaul9a6_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":6900471,"nickname":"扎西德勒","role":0}},{"content":"赞我","created_at":1459314253,"does_like":false,"id":886423,"likes_count":2,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/160404/e1ceffd49_a-w180","can_mobile_login":true,"guest_uuid":null,"id":5934522,"nickname":"果果","role":0}},{"content":"沙发","created_at":1459312961,"does_like":false,"id":886393,"likes_count":11,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/160202/5f00cfb4b_a-w180","can_mobile_login":false,"guest_uuid":null,"id":2565035,"nickname":"seven","role":0}},{"content":"，","created_at":1459303670,"does_like":false,"id":886333,"likes_count":3,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://ww3.sinaimg.cn/crop.0.0.720.720.1024/005ISW","can_mobile_login":false,"guest_uuid":null,"id":5697617,"nickname":"Thesongjust","role":0}},{"content":"沙发\u2026\u2026","created_at":1459303620,"does_like":false,"id":886332,"likes_count":3,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://ww3.sinaimg.cn/crop.0.0.720.720.1024/005ISW","can_mobile_login":false,"guest_uuid":null,"id":5697617,"nickname":"Thesongjust","role":0}},{"content":"沙发","created_at":1459303608,"does_like":false,"id":886330,"likes_count":3,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://ww3.sinaimg.cn/crop.0.0.720.720.1024/005ISW","can_mobile_login":false,"guest_uuid":null,"id":5697617,"nickname":"Thesongjust","role":0}},{"content":"沙发","created_at":1459303589,"does_like":false,"id":886329,"likes_count":3,"post_id":1040072,"reply_to_id":null,"user":{"avatar_url":"http://ww3.sinaimg.cn/crop.0.0.720.720.1024/005ISW","can_mobile_login":false,"guest_uuid":null,"id":5697617,"nickname":"Thesongjust","role":0}}]
     * paging : {"next_url":null}
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
        /**
         * next_url : null
         */

        private PagingEntity paging;
        /**
         * content : 占座
         * created_at : 1459564121
         * does_like : false
         * id : 903486
         * likes_count : 2
         * post_id : 1040072
         * reply_to_id : null
         * user : {"avatar_url":"http://img03.liwushuo.com/avatar/151010/ed0e691df_a-w180","can_mobile_login":true,"guest_uuid":null,"id":3002827,"nickname":"老王","role":0}
         */

        private List<CommentsEntity> comments;

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public void setComments(List<CommentsEntity> comments) {
            this.comments = comments;
        }

        public PagingEntity getPaging() {
            return paging;
        }

        public List<CommentsEntity> getComments() {
            return comments;
        }

        public static class PagingEntity {
            private Object next_url;

            public void setNext_url(Object next_url) {
                this.next_url = next_url;
            }

            public Object getNext_url() {
                return next_url;
            }
        }

        public static class CommentsEntity {
            private String content;
            private int created_at;
            private boolean does_like;
            private int id;
            private int likes_count;
            private int post_id;
            private Object reply_to_id;
            /**
             * avatar_url : http://img03.liwushuo.com/avatar/151010/ed0e691df_a-w180
             * can_mobile_login : true
             * guest_uuid : null
             * id : 3002827
             * nickname : 老王
             * role : 0
             */

            private UserEntity user;

            public void setContent(String content) {
                this.content = content;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public void setDoes_like(boolean does_like) {
                this.does_like = does_like;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public void setPost_id(int post_id) {
                this.post_id = post_id;
            }

            public void setReply_to_id(Object reply_to_id) {
                this.reply_to_id = reply_to_id;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public String getContent() {
                return content;
            }

            public int getCreated_at() {
                return created_at;
            }

            public boolean isDoes_like() {
                return does_like;
            }

            public int getId() {
                return id;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public int getPost_id() {
                return post_id;
            }

            public Object getReply_to_id() {
                return reply_to_id;
            }

            public UserEntity getUser() {
                return user;
            }

            public static class UserEntity {
                private String avatar_url;
                private boolean can_mobile_login;
                private Object guest_uuid;
                private int id;
                private String nickname;
                private int role;

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public void setCan_mobile_login(boolean can_mobile_login) {
                    this.can_mobile_login = can_mobile_login;
                }

                public void setGuest_uuid(Object guest_uuid) {
                    this.guest_uuid = guest_uuid;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public void setRole(int role) {
                    this.role = role;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public boolean isCan_mobile_login() {
                    return can_mobile_login;
                }

                public Object getGuest_uuid() {
                    return guest_uuid;
                }

                public int getId() {
                    return id;
                }

                public String getNickname() {
                    return nickname;
                }

                public int getRole() {
                    return role;
                }
            }
        }
    }
}
