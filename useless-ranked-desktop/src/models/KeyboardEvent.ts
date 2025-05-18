import { DataTypes, Model, Sequelize } from 'sequelize'

export class KeyboardEvent extends Model {
    public id!: number
    public keyCode!: number
    public readonly createdAt!: Date
    public readonly updatedAt!: Date
}

export function initKeyboardEvent(sequelize: Sequelize) {
    KeyboardEvent.init(
        {
            id: {
                type: DataTypes.INTEGER,
                autoIncrement: true,
                primaryKey: true,
            },
            keyCode: {
                type: DataTypes.INTEGER,
                allowNull: true,
            }
        },
        {
            sequelize,
            tableName: 'keyboard_events',
            modelName: 'KeyboardEvent',
            timestamps: true,
        }
    )
}