public class HotelControl extends AccessControl{
    private int validCardId = 123;
    private int validRoomNumber = 111;

    @Override
    public boolean validateAccess(int cardId, int roomNumber) {
        if(cardId == this.validCardId && roomNumber == this.validRoomNumber){
            System.out.println("Access succesful");
            return true;

        }else {
            System.out.println("Access denied");
            return false;
        }
    }
}
