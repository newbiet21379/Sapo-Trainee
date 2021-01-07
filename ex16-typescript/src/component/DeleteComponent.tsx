import React from 'react';
import { Button } from 'reactstrap';
import CategoryService from '../service/Category.service';



export default function Delete(props:any) {

    const deleteCategory = () => {
        CategoryService.delete(props.currentCategory.id)
            .then((response:any) => {
                console.log(response.data);
                props.reload();
            })
            .catch((e:any) => {
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


