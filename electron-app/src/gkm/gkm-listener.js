const { EventEmitter2 } = require('eventemitter2');
const { spawn } = require('child_process');
const StringUtils = require('../utils/string-utils');
const path = require('path');
const { default: GkmKeyboardEvent } = require('./model/gkm-keyboard-event');
const { default: GkmMouseWheelEvent } = require('./model/gkm-mousewheel-event');
const { default: GkmMouseEvent } = require('./model/gkm-mouse-event');

const events = new EventEmitter2({ wildcard: true });
const gkm = spawn('java', ['-jar', path.join(__dirname, '../jar-lib/gkm.jar')]);

gkm.stdout.on('data', function (data) {
    data = data.toString().split(/\r\n|\r|\n/).filter(function (item) { return item; });
    for (var i in data) {
        var parts = StringUtils.splitOnce(data[i], ':');
        events.emit(parts[0], parts[1]);
        switch (parts[0]) {
            case 'keyboard':
                var parsedData = JSON.parse(parts[1]);
                var keyboardEvent = new GkmKeyboardEvent(parsedData.eventType, parsedData.keyCode);
                events.emit('keyboard', keyboardEvent);
                break;
            case 'mouse':
                var parsedData = JSON.parse(parts[1]);
                var mouseEvent = new GkmMouseEvent(parsedData.eventType, parsedData.button, parsedData.clickCount, parsedData.x, parsedData.y);
                events.emit('mouse', mouseEvent);
                break;
            case 'mousewheel':
                var parsedData = JSON.parse(parts[1]);
                var mouseWheelEvent = new GkmMouseWheelEvent(parsedData.direction, parsedData.scrollAmount);
                events.emit('mousewheel', mouseWheelEvent);
                break;
            default:
                break;
        }
    }
});

module.exports = {
    events: events
};