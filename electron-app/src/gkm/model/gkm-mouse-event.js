export default class GkmMouseEvent {
    eventType;
    button;
    clickCount;
    x;
    y;

    GkmMouseEvent(eventType, button, clickCount, x, y) {
        this.eventType = eventType;
        this.button = button;
        this.clickCount = clickCount;
        this.x = x;
        this.y = y;
    }
}