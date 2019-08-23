import * as React from 'react';

import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label, Alert, Row, Col } from 'reactstrap';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Link } from 'react-router-dom';
import GoogleLogin from 'react-google-login';
import { LoginService } from './LoginService';
import { Modal } from 'antd';

export interface ILoginModalProps {
  showModal: boolean;
  loginError: boolean;
  handleLogin: Function;
  handleClose: Function;
}

class LoginModal extends React.Component<ILoginModalProps> {
  async handleGoogleLogin(response) {
    LoginService.signInWithGoogle({ idToken: response.tokenObj.id_token });
  }

  handleFailure = error => {
    throw new Error('error' + error);
  };

  render() {
    return (
      <div>
        <GoogleLogin
          clientId="1078337195896-k13cklfcjl2i49mo3j8foglcjvrodh7n.apps.googleusercontent.com"
          buttonText="Sign In with Google"
          onSuccess={this.handleGoogleLogin}
          onFailure={this.handleFailure}
          hostedDomain="coditas.com"
        />
      </div>
    );
  }
}

export default LoginModal;
