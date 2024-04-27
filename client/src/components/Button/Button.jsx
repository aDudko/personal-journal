import './Button.css';
import { memo } from 'react';

function Button() {
	return (
		<button className='button accent'>Save</button>
	);
}

export default memo(Button);