import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TeamMembers from './team-members';
import TeamMembersDetail from './team-members-detail';
import TeamMembersUpdate from './team-members-update';
import TeamMembersDeleteDialog from './team-members-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TeamMembersUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TeamMembersUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TeamMembersDetail} />
      <ErrorBoundaryRoute path={match.url} component={TeamMembers} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TeamMembersDeleteDialog} />
  </>
);

export default Routes;
