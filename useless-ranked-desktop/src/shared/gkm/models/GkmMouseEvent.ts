import { MouseEventType } from '../../../enums/MouseEventType';

export class GkmMouseEvent {
    eventType: MouseEventType;
    button: number;
    x: number;
    y: number;

    constructor(eventType: MouseEventType, button: number, x: number, y: number) {
        this.eventType = eventType;
        this.button = button;
        this.x = x;
        this.y = y;
    }
}