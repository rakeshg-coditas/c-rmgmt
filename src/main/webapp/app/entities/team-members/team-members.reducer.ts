import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITeamMembers, defaultValue } from 'app/shared/model/team-members.model';

export const ACTION_TYPES = {
  FETCH_TEAMMEMBERS_LIST: 'teamMembers/FETCH_TEAMMEMBERS_LIST',
  FETCH_TEAMMEMBERS: 'teamMembers/FETCH_TEAMMEMBERS',
  CREATE_TEAMMEMBERS: 'teamMembers/CREATE_TEAMMEMBERS',
  UPDATE_TEAMMEMBERS: 'teamMembers/UPDATE_TEAMMEMBERS',
  DELETE_TEAMMEMBERS: 'teamMembers/DELETE_TEAMMEMBERS',
  RESET: 'teamMembers/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITeamMembers>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TeamMembersState = Readonly<typeof initialState>;

// Reducer

export default (state: TeamMembersState = initialState, action): TeamMembersState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TEAMMEMBERS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TEAMMEMBERS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TEAMMEMBERS):
    case REQUEST(ACTION_TYPES.UPDATE_TEAMMEMBERS):
    case REQUEST(ACTION_TYPES.DELETE_TEAMMEMBERS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TEAMMEMBERS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TEAMMEMBERS):
    case FAILURE(ACTION_TYPES.CREATE_TEAMMEMBERS):
    case FAILURE(ACTION_TYPES.UPDATE_TEAMMEMBERS):
    case FAILURE(ACTION_TYPES.DELETE_TEAMMEMBERS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEAMMEMBERS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEAMMEMBERS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TEAMMEMBERS):
    case SUCCESS(ACTION_TYPES.UPDATE_TEAMMEMBERS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TEAMMEMBERS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/team-members';

// Actions

export const getEntities: ICrudGetAllAction<ITeamMembers> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TEAMMEMBERS_LIST,
  payload: axios.get<ITeamMembers>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITeamMembers> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TEAMMEMBERS,
    payload: axios.get<ITeamMembers>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITeamMembers> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TEAMMEMBERS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITeamMembers> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TEAMMEMBERS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITeamMembers> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TEAMMEMBERS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
