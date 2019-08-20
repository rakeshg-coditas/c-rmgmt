export interface INotification {
  id?: string;
  to?: string;
  from?: string;
  message?: string;
  isRead?: boolean;
}

export const defaultValue: Readonly<INotification> = {
  isRead: false
};
