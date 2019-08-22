import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './team-members.reducer';
import { ITeamMembers } from 'app/shared/model/team-members.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITeamMembersProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TeamMembers extends React.Component<ITeamMembersProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { teamMembersList, match } = this.props;
    return (
      <div>
        <h2 id="team-members-heading">
          Team Members
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Team Members
          </Link>
        </h2>
        <div className="table-responsive">
          {teamMembersList && teamMembersList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Lead</th>
                  <th>Members</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {teamMembersList.map((teamMembers, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${teamMembers.id}`} color="link" size="sm">
                        {teamMembers.id}
                      </Button>
                    </td>
                    <td>{teamMembers.name}</td>
                    <td>{teamMembers.lead}</td>
                    <td>{teamMembers.members}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${teamMembers.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${teamMembers.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${teamMembers.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Team Members found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ teamMembers }: IRootState) => ({
  teamMembersList: teamMembers.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TeamMembers);
