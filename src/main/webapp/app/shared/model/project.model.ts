import { Moment } from 'moment';

export interface IProject {
  id?: string;
  name?: string;
  client?: string;
  billing_type?: string;
  team?: string;
  lead?: string;
  skills?: string;
  start_date?: Moment;
  end_date?: Moment;
}

export const defaultValue: Readonly<IProject> = {};
