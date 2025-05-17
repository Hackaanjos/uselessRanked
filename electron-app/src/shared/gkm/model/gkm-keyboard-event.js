export default class GkmKeyboardEvent {
    eventType;
    keyCode;

    constructor(eventType, keyCode) {
        this.eventType = eventType;
        this.keyCode = keyCode;
    }
}
