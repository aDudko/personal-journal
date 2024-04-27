import styles from './Input.module.css';
import cn from 'classnames';
import { forwardRef } from 'react';

const Input = forwardRef(function Input({ isValid, appearence = 'text', className, ...props }, ref) {
	return (
		<input ref={ ref } className={cn(className, {
			[styles['invalid']]: isValid,
			[styles['input-title']]: appearence == 'title',
			[styles['input']]: appearence == 'text'
		})} { ...props }/>
	);
});

export default Input;