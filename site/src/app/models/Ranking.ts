import { UserRanking } from "./UserRanking";

export class Ranking {

    name: string
    unit: string;
    topUsersList: Array<UserRanking>;

    constructor(name: string, unit: string, topUsersList: Array<UserRanking>) {
        this.name = name;
        this.unit = unit;
        this.topUsersList = topUsersList;
    }

}