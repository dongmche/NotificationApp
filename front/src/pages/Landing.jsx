import main from '../assets/images/main.svg';
import Wrapper from '../assets/wrappers/LandingPage.jsx';
import { Link } from 'react-router-dom';

const Landing = () => {
  return (
    <Wrapper>
      <div className='container page'>
        {/* info */}
        <div className='info'>
          <h1>
            The <span>Notification</span> app
          </h1>
          <p>
            Welcome to the biggest notification site in the world
          </p>
          <Link to='/register' className='btn btn-hero'>
            Login/Register
          </Link>
        </div>
        <img src={main} alt='job hunt' className='img main-img' />
      </div>
    </Wrapper>
  );
};

export default Landing;