import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TeamMembers from './team-members';
import Employee from './employee';
import Role from './role';
import Client from './client';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/team-members`} component={TeamMembers} />
      <ErrorBoundaryRoute path={`${match.url}/employee`} component={Employee} />
      <ErrorBoundaryRoute path={`${match.url}/role`} component={Role} />
      <ErrorBoundaryRoute path={`${match.url}/client`} component={Client} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
