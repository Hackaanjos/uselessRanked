import { DataTypes, Model, Sequelize } from "sequelize"

export class MouseClickEvent extends Model {
    public id!: number
    public button!: string
    public readonly createdAt!: Date
    public readonly updatedAt!: Date
}

export function initMouseClickEvent(sequelize: Sequelize) {
    MouseClickEvent.init(
        {
            id: {
                type: DataTypes.INTEGER,
                autoIncrement: true,
                primaryKey: true,
            },
            button: {
                type: DataTypes.STRING,
                allowNull: true,
            }
        },
        {
            sequelize,
            tableName: 'mouse_click_events',
            modelName: 'MouseClickEvent',
            timestamps: true,
        }
    )
}