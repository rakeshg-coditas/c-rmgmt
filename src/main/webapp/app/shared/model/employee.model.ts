import { Moment } from 'moment';

export interface IEmployee {
  id?: string;
  email?: string;
  name?: string;
  skills?: string;
  projects?: string;
  picture?: string;
  skill_category?: string;
  report_to?: string;
  team?: string;
  career_start_date?: Moment;
  joining_date?: Moment;
  role?: string;
  employeeId?: string;
  billable?: string;
  officeLocation?: string;
  employmentType?: string;
}

export const defaultValue: Readonly<IEmployee> = {};
