export interface ITeamMembers {
  id?: string;
  name?: string;
  lead?: string;
  members?: string;
}

export const defaultValue: Readonly<ITeamMembers> = {};
