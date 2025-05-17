const { Model } = require('objection');

class KeyboardEvent extends Model {
  static get tableName() {
    return 'keyboard_events';
  }

  static get idColumn() {
    return 'id';
  }

  static get jsonSchema() {
    return {
      type: 'object',
      required: ['keyCode'],
      properties: {
        id: { type: 'integer' },
        keyCode: { type: 'integer' },
        createdAt: { type: 'string', format: 'date-time' },
      }
    };
  }

  $beforeInsert() {
    this.createdAt = new Date().toISOString();
  }
}

module.exports = KeyboardEvent;
