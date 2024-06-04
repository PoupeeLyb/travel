        package JieZhu.pojo;
        import lombok.*;
        import java.util.Date;
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class Diary {
            private int id;
            private String username;
            private String avatarUrl;
            private Date time;
            private String title;
            private String content;
            private String imgUrls;  // 使用字符串存储JSON数据
            private int clicks;
            private int scene_id;
        }