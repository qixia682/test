// 定义一个接口，限制每个Person对象的格式
export interface MatchInter {
    id:number,
    name:string,
}

// 定义一个自定义类型Persons
export type MatchList = Array<MatchInter>