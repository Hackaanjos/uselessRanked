exports.up = function (knex) {
    return knex.schema.createTable('keyboard_events', (table) => {
        table.increments('id').primary();
        table.integer('keyCode').notNullable();
        table.timestamp('createdAt').defaultTo(knex.fn.now());
    });
};

exports.down = function (knex) {
    return knex.schema.dropTable('keyboard_events');
};
