interface Sort {
    sorted: boolean;
    empty: boolean;
    unsorted: boolean;
}

export interface Pageable {
    pageNumber: number,
    pageSize: number,
    sort: Sort
    offset: number,
    paged: boolean,
    unpaged: boolean
}

export interface PaginatedList<T> {
    content: Array[T];
    last: boolean,
    totalPages: number,
    totalElements: number,
    first: boolean,
    size: number,
    number: number,
    sort: Sort,
    numberOfElements: 3,
    empty: false
}
