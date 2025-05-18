import { UserRanking } from "./UserRanking";
import { PeriodType } from "../../utils/enums/PeriodType";

export class Ranking {

    name: string;
    unit: string;
    data: Record<PeriodType, UserRanking[]>;

    constructor(name: string, unit: string, rankings: Record<PeriodType, UserRanking[]>) {
        this.name = name;
        this.unit = unit;
        this.data = rankings;
    }

}
