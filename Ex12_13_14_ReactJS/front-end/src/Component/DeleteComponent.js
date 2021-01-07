import React from 'react';
import { Button } from 'reactstrap';
import CategoryService from '../service/Category.service';



export default function Delete(props) {

    const deleteCategory = () => {
        CategoryService.delete(props.currentCategory.category_id)
            .then(response => {
                console.log(response.data);
                props.reload();
            })
            .catch(e => {
                console.log(e);
            });
    }
    return (
        <div>
            <Button color="danger" onClick={deleteCategory}>
                Delete
                    </Button>
        </div>
    );
}


