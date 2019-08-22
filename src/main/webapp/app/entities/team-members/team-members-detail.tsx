import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './team-members.reducer';
import { ITeamMembers } from 'app/shared/model/team-members.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITeamMembersDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TeamMembersDetail extends React.Component<ITeamMembersDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { teamMembersEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TeamMembers [<b>{teamMembersEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{teamMembersEntity.name}</dd>
            <dt>
              <span id="lead">Lead</span>
            </dt>
            <dd>{teamMembersEntity.lead}</dd>
            <dt>
              <span id="members">Members</span>
            </dt>
            <dd>{teamMembersEntity.members}</dd>
          </dl>
          <Button tag={Link} to="/entity/team-members" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/team-members/${teamMembersEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ teamMembers }: IRootState) => ({
  teamMembersEntity: teamMembers.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TeamMembersDetail);
