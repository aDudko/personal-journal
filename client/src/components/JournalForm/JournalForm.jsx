import styles from './JournalForm.module.css';
import Button from '../Button/Button';
import { useEffect, useReducer, useRef } from 'react';
import cn from 'classnames';
import { INITIAL_STATE, formReducer } from './JournalForm.state';
import Input from '../Input/Input';

function JournalForm({ onSubmit, data, onDelete }) {
	const [formState, dispatchForm] = useReducer(formReducer, INITIAL_STATE);
	const { isValid, isFormReadyToSubmit, values } = formState;
	const titleRef = useRef();
	const dateRef = useRef();
	const textRef = useRef();

	const focusError = (isValid) => {
		switch(true) {
		case !isValid.title:
			titleRef.current.focus();
			break;
		case !isValid.date:
			dateRef.current.focus();
			break;
		case !isValid.text:
			textRef.current.focus();
			break;
		}
	};

	useEffect(() => {
		if (!data) {
			dispatchForm({ type: 'CLEAR' });
			dispatchForm({ type: 'SET_VALUE', payload: { }});
		}
		dispatchForm({ type: 'SET_VALUE', payload: { ...data }});
	}, [data]);

	useEffect(() => {
		let timerId;
		if (!isValid.date || !isValid.text || !isValid.title) {
			focusError(isValid);
			timerId = setTimeout(() => {
				dispatchForm({ type: 'RESET_VALIDITY' });
			}, 2000);
		}
		return () => {
			clearTimeout(timerId);
		};
	}, [isValid]);

	useEffect(() => {
		if (isFormReadyToSubmit) {
			onSubmit(values);
			dispatchForm({ type: 'CLEAR' });
			dispatchForm({ type: 'SET_VALUE', payload: { }});
		}
	}, [isFormReadyToSubmit, values, onSubmit]);

	useEffect(() => {
		dispatchForm({ type: 'SET_VALUE', payload: { }});
	}, []);

	const onChange = (e) => {
		dispatchForm({ type: 'SET_VALUE', payload: { [e.target.name]: e.target.value }});
	};

	const addJournalItem = (e) => {
		e.preventDefault();
		dispatchForm({ type: 'SUBMIT' });
	};

	const deleteJournalItem = () => {
		onDelete(data.id);
		dispatchForm({ type: 'CLEAR' });
		dispatchForm({ type: 'SET_VALUE', payload: { }});
	};

	return (
		<form className={styles['journal-form']} onSubmit={addJournalItem}>
			<div className={styles['form-row']}>
				<Input appearence="title" type='text' ref={titleRef} onChange={onChange} value={values.title} name='title' isValid={!isValid.title}/>
				{data?.id && <button className={styles['delete']} type="button" onClick={deleteJournalItem}>
					<img src="/archive.svg" alt="Delete button" />
				</button>}
			</div>
			<div className={styles['form-row']}>
				<label htmlFor="date" className={styles['form-label']}>
					<img src='/calendar.svg' alt="Calendar's icon"/>
					<span>Date</span>
				</label>
				<Input type='date' ref={dateRef} onChange={onChange} name='date' value={values.date} id="date" isValid={!isValid.title}/>
			</div>
			<div className={styles['form-row']}>
				<label htmlFor="tag" className={styles['form-label']}>
					<img src='/folder.svg' alt="Folder's icon" />
					<span>Tags</span>
				</label>
				<Input type='text' onChange={onChange} id="tag" value={values.tag} name='tag' />
			</div>
			<textarea ref={textRef} name="text" id="" onChange={onChange} value={values.text} cols="30" rows="10" className={cn(styles['input'], {
				[styles['invalid']]: !isValid.text
			})}></textarea>
			<Button>Save</Button>
		</form>
	);
}

export default JournalForm;