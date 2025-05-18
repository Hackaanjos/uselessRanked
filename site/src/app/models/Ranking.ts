import { UserRanking } from "./UserRanking";
import { PeriodType } from "../../utils/enums/PeriodType";

export class Ranking {

    description: string;
    name: string;
    unit: string;
    data: Record<PeriodType, UserRanking[]>;

    constructor(description: string, name: string, unit: string, rankings: Record<PeriodType, UserRanking[]>) {
        this.description = description;
        this.name = name;
        this.unit = unit;
        this.data = rankings;
    }

}
