import React from 'react';
import { useForm } from 'react-hook-form';
import styles from '../styles/searchform.module.css';

export default function SearchForm(props) {
    const { register, handleSubmit } = useForm();

    return (
        <form className={styles.container} onSubmit={handleSubmit((data) => {
            props.search(data.name, data.manufacturer, data.crew, data.symmetric, data.asymmetric, data.trapeze);
    })}>
        <div>
            <label htmlFor="name">Class name:</label>
            <input type="text"
                name="name"
                placeholder="Class name"
                {...register("name")}/>
        </div>
        <div>
            <label htmlFor="manufacturer">Manufacturer:</label>
            <input type="text"
                name="manufacturer"
                placeholder="Manufacturer"
                {...register("manufacturer")}/>
        </div>
        <div>
            <label htmlFor="crew">Number of crew:</label>
            <input type="number"
                name="crew"
                placeholder="Crew"
                {...register("crew")}/>
        </div>
        <div>
            <input type="checkbox"
                name="symmetric"
                {...register("symmetric")} />
            <label className={styles.checkboxText} htmlFor="symmetric">Symmetric spinnaker</label>
        </div>
        <div>
            <input type="checkbox"
                name="asymmetric"
                {...register("asymmetric")} />
            <label className={styles.checkboxText} l htmlFor="asymmetric">Asymmetric spinnaker</label>
        </div>
        <div>
            <input type="checkbox"
                name="trapeze"
                {...register("trapeze")}/>
            <label className={styles.checkboxText} htmlFor="trapeze">Trapeze</label>
        </div>
        <div>
            <input type="submit" value="Search"/>
        </div>
        </form>
    )
}
