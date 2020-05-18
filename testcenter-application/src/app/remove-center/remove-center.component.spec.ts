import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveCenterComponent } from './remove-center.component';

describe('RemoveCenterComponent', () => {
  let component: RemoveCenterComponent;
  let fixture: ComponentFixture<RemoveCenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoveCenterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
