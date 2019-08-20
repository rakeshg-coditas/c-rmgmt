export interface IClient {
  id?: string;
  name?: string;
  contact?: string;
  email?: string;
}

export const defaultValue: Readonly<IClient> = {};
