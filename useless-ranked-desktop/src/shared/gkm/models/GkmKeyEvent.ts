import { KeyboardEventType } from '../../../enums/KeyboardEventType';
export default class GkmKeyEvent {

    eventType: KeyboardEventType;
    keyCode: number;

    constructor(eventType: KeyboardEventType, keyCode: number) {
        this.eventType = eventType;
        this.keyCode = keyCode;
    }
}
