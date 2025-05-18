import { KeyboardEvent } from "../../models/KeyboardEvent";
import { MouseClickEvent } from "../../models/MouseClickEvent";
import { GkmListener } from "./GkmListener";
import GkmKeyEvent from "./models/GkmKeyEvent";
import { GkmMouseEvent } from "./models/GkmMouseEvent";

export class GkmLogger {
    private static instance: GkmLogger;

    public static getInstance(): GkmLogger {
        if (!GkmLogger.instance) {
            GkmLogger.instance = new GkmLogger();
        }
        return GkmLogger.instance;
    }

    public async onKeyPressed(event: GkmKeyEvent): Promise<void> {
        await KeyboardEvent.create({
            eventType: event.eventType,
            keyCode: event.keyCode
        });
    }

    public async onMouseClicked(event: GkmMouseEvent): Promise<void> {
        await MouseClickEvent.create({
            eventType: event.eventType,
            button: event.button,
        });
    }

    public start(): void {
        GkmListener.getInstance().start();
        GkmListener.getInstance().onKeyPressed(this.onKeyPressed.bind(this));
        GkmListener.getInstance().onMouseClicked(this.onMouseClicked.bind(this));
    }

    public stop(): void {
        GkmListener.getInstance().stop();
    }
}