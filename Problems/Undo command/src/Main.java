interface Movable {
    int getX();

    int getY();

    void setX(int newX);

    void setY(int newY);




}

interface Storable {
    int getInventoryLength();

    String getInventoryItem(int index);

    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();
    void undo();
}

class CommandMove implements Command {
    Movable entity;
    int xMovement;
    int yMovement;

    @Override
    public void execute() {
        entity.setX(entity.getX() + xMovement);
        entity.setY(entity.getY() + yMovement);
    }

    @Override
    public void undo() {
        entity.setX(entity.getX() - xMovement);
        entity.setY(entity.getY() - yMovement);
    }
}

class CommandPutItem implements Command {
    Storable entity;
    String item;

    Integer slotNum;  // undo functionality

    @Override
    public void execute() {
        int invLength = entity.getInventoryLength();

        for (int i = 0; i < invLength; i++) {
            if (null == entity.getInventoryItem(i)) {
                entity.setInventoryItem(i, item);
                slotNum = i;
                break;
            }
        }
    }

    @Override
    public void undo() {
        if (null != slotNum) {
            entity.setInventoryItem(slotNum, null);
        }
    }
}