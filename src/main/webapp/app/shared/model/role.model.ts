export interface IRole {
  id?: string;
  name?: string;
  isDeleted?: boolean;
}

export const defaultValue: Readonly<IRole> = {
  isDeleted: false
};
