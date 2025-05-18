export interface Achievement {
    id: string;
    name: string;
    description: string;
    completed: boolean;
    icon: string;
    progress?: number;
    totalRequired?: number;
} 