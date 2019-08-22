import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './team-members.reducer';
import { ITeamMembers } from 'app/shared/model/team-members.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITeamMembersUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITeamMembersUpdateState {
  isNew: boolean;
}

export class TeamMembersUpdate extends React.Component<ITeamMembersUpdateProps, ITeamMembersUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { teamMembersEntity } = this.props;
      const entity = {
        ...teamMembersEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/team-members');
  };

  render() {
    const { teamMembersEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cRmgmtApp.teamMembers.home.createOrEditLabel">Create or edit a TeamMembers</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : teamMembersEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="team-members-id">ID</Label>
                    <AvInput id="team-members-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="team-members-name">
                    Name
                  </Label>
                  <AvField id="team-members-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="leadLabel" for="team-members-lead">
                    Lead
                  </Label>
                  <AvField id="team-members-lead" type="text" name="lead" />
                </AvGroup>
                <AvGroup>
                  <Label id="membersLabel" for="team-members-members">
                    Members
                  </Label>
                  <AvField id="team-members-members" type="text" name="members" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/team-members" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  teamMembersEntity: storeState.teamMembers.entity,
  loading: storeState.teamMembers.loading,
  updating: storeState.teamMembers.updating,
  updateSuccess: storeState.teamMembers.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TeamMembersUpdate);
