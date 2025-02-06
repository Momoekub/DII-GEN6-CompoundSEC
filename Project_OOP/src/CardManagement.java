public interface CardManagement {
    // เมธอดจาก interface
    void addCard(int roomNumber, int cardId);
    void updateCard(int roomNumber, int newCardId);
    void changeRoomId(int oldRoomNumber, int newRoomNumber);
    void changeCardId(int roomNumber, int newCardId);
}
