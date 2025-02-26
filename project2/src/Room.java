    public class Room {
        private int roomId;
        private boolean available;
        private String userName;
        private String password;

        public Room(int roomId) {
            this.roomId = roomId;
            this.available = true;
            this.userName = null;
            this.password = null;
        }

        public int getRoomId() {
            return roomId;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }