import { Status } from "../enum/status.enum";

export interface Server{
    id:number;
    ipAddress: string;
    name: string;
    memeory: string;
    imageUrl: string;
    status: Status;
}