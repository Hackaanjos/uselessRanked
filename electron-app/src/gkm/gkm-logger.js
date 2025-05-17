const gkmListener = require('./gkm-listener');
const KeyboardEvent = require('../models/keyboard-event');
const MouseClickEvent = require('../models/mouse-click-event');


function initialize() {
    gkmListener.events.on('keyboard', async function (keyboardEventData) {
        if (keyboardEventData.eventType !== 'RELEASED') return;

        await KeyboardEvent.query().insert({
            keyCode: keyboardEventData.keyCode,
        });
    });

    gkmListener.events.on('mouse', async function (mouseEvent) {
        if (mouseEvent.eventType !== "CLICKED") return;

        await MouseClickEvent.query().insert({
            clickButton: mouseEvent.button,
        });
    });

    gkmListener.events.on('mousewheel', function (mousewheelEvent) {
        console.log('Mousewheel event:', mousewheelEvent);
    });
}

module.exports = {
    initialize
};
