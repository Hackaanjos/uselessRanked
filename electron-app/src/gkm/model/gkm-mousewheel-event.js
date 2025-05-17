export default class GkmMouseWheelEvent {
    direction;
    scrollAmount;

    constructor(direction, scrollAmount) {
        this.direction = direction;
        this.scrollAmount = scrollAmount;
    }
}