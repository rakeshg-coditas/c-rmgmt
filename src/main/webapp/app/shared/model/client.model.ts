export interface IClient {
  id?: string;
  name?: string;
  email?: string;
  address?: string;
  isActive?: boolean;
}

export const defaultValue: Readonly<IClient> = {
  isActive: false
};
