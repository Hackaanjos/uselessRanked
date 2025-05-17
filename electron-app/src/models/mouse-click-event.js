const { Model } = require('objection');

class MouseClickEvent extends Model {
    static get tableName() {
        return 'mouse_click_event';
    }

    static get idColumn() {
        return 'id';
    }

    static get jsonSchema() {
        return {
            type: 'object',
            required: ['clickButton'],
            properties: {
                id: { type: 'integer' },
                clickButton: { type: 'integer' },
                createdAt: { type: 'string', format: 'date-time' },
            }
        };
    }

    $beforeInsert() {
        this.createdAt = new Date().toISOString();
    }
}

module.exports = MouseClickEvent;